/**
 * Module for displaying "Waiting for..." dialog using Bootstrap
 *
 * @author Eugene Maslovich <ehpc@em42.ru>
 */

(function (root, factory) {
	'use strict';

	if (typeof define === 'function' && define.amd) {
		define(['jquery'], function ($) {
			return (root.waitingDialog = factory($));
		});
	}
	else {
		root.waitingDialog = root.waitingDialog || factory(root.jQuery);
	}

}(this, function ($) {
	'use strict';

	/**
	 * Dialog DOM constructor
	 */
	function constructDialog($dialog) {
		// Deleting previous incarnation of the dialog
		if ($dialog) {
			$dialog.remove();
		}
		return $(
			'<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
				'<div class="modal-dialog modal-m">' +
					'<div class="modal-content">' +
						'<div class="modal-header" style="display: none;"></div>' +
						'<div class="modal-body">' +
							'<div class="progress progress-striped active" style="margin-bottom:0;">' +
								'<div class="progress-bar" style="width: 100%"></div>' +
							'</div>' +
						'</div>' +
					'</div>' +
				'</div>' +
			'</div>'
		);
	}

	var $dialog, // Dialog object
		settings; // Dialog settings

	return {
		/**
		 * Opens our dialog
		 * @param message Custom message
		 * @param options Custom options:
		 *   options.headerText - if the option is set to boolean false,
		 *     it will hide the header and "message" will be set in a paragraph above the progress bar.
		 *     When headerText is a not-empty string, "message" becomes a content
		 *     above the progress bar and headerText string will be set as a text inside the H3;
		 *   options.headerSize - this will generate a heading corresponding to the size number. Like <h1>, <h2>, <h3> etc;
		 *   options.headerClass - extra class(es) for the header tag;
		 *   options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
		 *   options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning";
		 *   options.contentElement - determines the tag of the content element.
		 *     Defaults to "p", which will generate a <p> tag;
		 *   options.contentClass - extra class(es) for the content tag.
		 */
		show: function (message, options) {
			// Assigning defaults
			if (typeof options === 'undefined') {
				options = {};
			}
			if (typeof message === 'undefined') {
				message = 'Loading';
			}
			settings = $.extend({
				headerText: '',
				headerSize: 3,
				headerClass: '',
				dialogSize: 'm',
				progressType: '',
				contentElement: 'p',
				contentClass: 'content',
				onHide: null, // This callback runs after the dialog was hidden
				onShow: null // This callback runs after the dialog was shown
			}, options);

			var $headerTag, $contentTag;

			$dialog = constructDialog($dialog);

			// Configuring dialog
			$dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
			$dialog.find('.progress-bar').attr('class', 'progress-bar');
			if (settings.progressType) {
				$dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
			}

			// Generate header tag
			$headerTag = $('<h' + settings.headerSize + ' />');
			$headerTag.css({ 'margin': 0 });
			if (settings.headerClass) {
				$headerTag.addClass(settings.headerClass);
			}

			// Generate content tag
			$contentTag = $('<' + settings.contentElement + ' />');
			if (settings.contentClass) {
				$contentTag.addClass(settings.contentClass);
			}

			if (settings.headerText === false) {
				$contentTag.html(message);
				$dialog.find('.modal-body').prepend($contentTag);
			}
			else if (settings.headerText) {
				$headerTag.html(settings.headerText);
				$dialog.find('.modal-header').html($headerTag).show();

				$contentTag.html(message);
				$dialog.find('.modal-body').prepend($contentTag);
			}
			else {
				$headerTag.html(message);
				$dialog.find('.modal-header').html($headerTag).show();
			}

			// Adding callbacks
			if (typeof settings.onHide === 'function') {
				$dialog.off('hidden.bs.modal').on('hidden.bs.modal', function () {
					settings.onHide.call($dialog);
				});
			}
			if (typeof settings.onShow === 'function') {
				$dialog.off('shown.bs.modal').on('shown.bs.modal', function () {
					settings.onShow.call($dialog);
				});
			}
			// Opening dialog
			$dialog.modal();
		},
		/**
		 * Closes dialog
		 */
		hide: function () {
			if (typeof $dialog !== 'undefined') {
				$dialog.modal('hide');
			}
		},
		/**
		 * Changes or displays current dialog message
		 */
		message: function (newMessage) {
			if (typeof $dialog !== 'undefined') {
				if (typeof newMessage !== 'undefined') {
					return $dialog.find('.modal-header>h' + settings.headerSize).html(newMessage);
				}
				else {
					return $dialog.find('.modal-header>h' + settings.headerSize).html();
				}
			}
		}
	};

}));

/**
 * Module for displaying success or fail result tip using Bootstrap
 *
 * @author fuwei 2017-1-18 17:15
 */
(function (root, factory) {
	'use strict';

	if (typeof define === 'function' && define.amd) {
		define(['jquery'], function ($) {
			return (root.tip = factory($));
		});
	}
	else {
		root.tip = root.tip || factory(root.jQuery);
	}

}(this, function ($) {
	'use strict';

	/**
	 * Dialog DOM constructor
	 */
	function constructTip($resultTip) {
		// Deleting previous incarnation of the dialog
		if ($resultTip) {
			$resultTip.remove();
		}
		return $(
			'<div id="resultTip" class="navbar-fixed-top">'+
                 '<div id="successTip" style="display:none;" class="alert alert-success text-center"><strong>操作成功!</strong></div>' +
                 '<div id="failTip" style="display:none;" class="alert alert-danger text-center"><strong>操作失败!</strong> 原因:<span id="exceptionMsg"></span></div>' +
             '</div>'
		);
	}

	var $resultTip, // Dialog object
		settings; // Dialog settings
	var $body =	$(document.body);

	return {
		show: function (message, options) {
			// Assigning defaults
			if (typeof options === 'undefined') {
				options = {};
			}
			if (typeof message === 'undefined') {
				message = '';
			}
			settings = $.extend({
				type: 'success',//success or fail
			}, options);

			$resultTip = constructTip($resultTip);
            if(!$resultTip.parent().length) {
                $resultTip.appendTo($body);
            }
            var $tip;
			// Configuring dialog
			if (settings.type === 'success') {
			    $tip = $resultTip.find('#successTip');
			} else {
                $tip = $resultTip.find('#failTip');
                $tip.find('#exceptionMsg').text(message);
			}
			$tip.show(300).delay(3000).hide(300);
		}
	};

}));

/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (value !== undefined && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setTime(+t + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {};

		// To prevent the for loop in the first place assign an empty array
		// in case there are no cookies at all. Also prevents odd result when
		// calling $.cookie().
		var cookies = document.cookie ? document.cookie.split('; ') : [];

		for (var i = 0, l = cookies.length; i < l; i++) {
			var parts = cookies[i].split('=');
			var name = decode(parts.shift());
			var cookie = parts.join('=');

			if (key && key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		if ($.cookie(key) === undefined) {
			return false;
		}

		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));

/*!art-template - Template Engine | http://aui.github.com/artTemplate/*/
!function() {
    function a(a) {
        return a.replace(t, "").replace(u, ",").replace(v, "").replace(w, "").replace(x, "").split(y)
    }
    function b(a) {
        return "'" + a.replace(/('|\\)/g, "\\$1").replace(/\r/g, "\\r").replace(/\n/g, "\\n") + "'"
    }
    function c(c, d) {
        function e(a) {
            return m += a.split(/\n/).length - 1,
            k && (a = a.replace(/\s+/g, " ").replace(/<!--[\w\W]*?-->/g, "")),
            a && (a = s[1] + b(a) + s[2] + "\n"),
            a
        }
        function f(b) {
            var c = m;
            if (j ? b = j(b, d) : g && (b = b.replace(/\n/g,
            function() {
                return m++,
                "$line=" + m + ";"
            })), 0 === b.indexOf("=")) {
                var e = l && !/^=[=#]/.test(b);
                if (b = b.replace(/^=[=#]?|[\s;]*$/g, ""), e) {
                    var f = b.replace(/\s*\([^\)]+\)/, "");
                    n[f] || /^(include|print)$/.test(f) || (b = "$escape(" + b + ")")
                } else b = "$string(" + b + ")";
                b = s[1] + b + s[2]
            }
            return g && (b = "$line=" + c + ";" + b),
            r(a(b),
            function(a) {
                if (a && !p[a]) {
                    var b;
                    b = "print" === a ? u: "include" === a ? v: n[a] ? "$utils." + a: o[a] ? "$helpers." + a: "$data." + a,
                    w += a + "=" + b + ",",
                    p[a] = !0
                }
            }),
            b + "\n"
        }
        var g = d.debug,
        h = d.openTag,
        i = d.closeTag,
        j = d.parser,
        k = d.compress,
        l = d.escape,
        m = 1,
        p = {
            $data: 1,
            $filename: 1,
            $utils: 1,
            $helpers: 1,
            $out: 1,
            $line: 1
        },
        q = "".trim,
        s = q ? ["$out='';", "$out+=", ";", "$out"] : ["$out=[];", "$out.push(", ");", "$out.join('')"],
        t = q ? "$out+=text;return $out;": "$out.push(text);",
        u = "function(){var text=''.concat.apply('',arguments);" + t + "}",
        v = "function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);" + t + "}",
        w = "'use strict';var $utils=this,$helpers=$utils.$helpers," + (g ? "$line=0,": ""),
        x = s[0],
        y = "return new String(" + s[3] + ");";
        r(c.split(h),
        function(a) {
            a = a.split(i);
            var b = a[0],
            c = a[1];
            1 === a.length ? x += e(b) : (x += f(b), c && (x += e(c)))
        });
        var z = w + x + y;
        g && (z = "try{" + z + "}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:" + b(c) + ".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}");
        try {
            var A = new Function("$data", "$filename", z);
            return A.prototype = n,
            A
        } catch(B) {
            throw B.temp = "function anonymous($data,$filename) {" + z + "}",
            B
        }
    }
    var d = function(a, b) {
        return "string" == typeof b ? q(b, {
            filename: a
        }) : g(a, b)
    };
    d.version = "3.0.0",
    d.config = function(a, b) {
        e[a] = b
    };
    var e = d.defaults = {
        openTag: "<%",
        closeTag: "%>",
        escape: !0,
        cache: !0,
        compress: !1,
        parser: null
    },
    f = d.cache = {};
    d.render = function(a, b) {
        return q(a, b)
    };
    var g = d.renderFile = function(a, b) {
        var c = d.get(a) || p({
            filename: a,
            name: "Render Error",
            message: "Template not found"
        });
        return b ? c(b) : c
    };
    d.get = function(a) {
        var b;
        if (f[a]) b = f[a];
        else if ("object" == typeof document) {
            var c = document.getElementById(a);
            if (c) {
                var d = (c.value || c.innerHTML).replace(/^\s*|\s*$/g, "");
                b = q(d, {
                    filename: a
                })
            }
        }
        return b
    };
    var h = function(a, b) {
        return "string" != typeof a && (b = typeof a, "number" === b ? a += "": a = "function" === b ? h(a.call(a)) : ""),
        a
    },
    i = {
        "<": "&#60;",
        ">": "&#62;",
        '"': "&#34;",
        "'": "&#39;",
        "&": "&#38;"
    },
    j = function(a) {
        return i[a]
    },
    k = function(a) {
        return h(a).replace(/&(?![\w#]+;)|[<>"']/g, j)
    },
    l = Array.isArray ||
    function(a) {
        return "[object Array]" === {}.toString.call(a)
    },
    m = function(a, b) {
        var c, d;
        if (l(a)) for (c = 0, d = a.length; d > c; c++) b.call(a, a[c], c, a);
        else for (c in a) b.call(a, a[c], c)
    },
    n = d.utils = {
        $helpers: {},
        $include: g,
        $string: h,
        $escape: k,
        $each: m
    };
    d.helper = function(a, b) {
        o[a] = b
    };
    var o = d.helpers = n.$helpers;
    d.onerror = function(a) {
        var b = "Template Error\n\n";
        for (var c in a) b += "<" + c + ">\n" + a[c] + "\n\n";
        "object" == typeof console && console.error(b)
    };
    var p = function(a) {
        return d.onerror(a),
        function() {
            return "{Template Error}"
        }
    },
    q = d.compile = function(a, b) {
        function d(c) {
            try {
                return new i(c, h) + ""
            } catch(d) {
                return b.debug ? p(d)() : (b.debug = !0, q(a, b)(c))
            }
        }
        b = b || {};
        for (var g in e) void 0 === b[g] && (b[g] = e[g]);
        var h = b.filename;
        try {
            var i = c(a, b)
        } catch(j) {
            return j.filename = h || "anonymous",
            j.name = "Syntax Error",
            p(j)
        }
        return d.prototype = i.prototype,
        d.toString = function() {
            return i.toString()
        },
        h && b.cache && (f[h] = d),
        d
    },
    r = n.$each,
    s = "break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",
    t = /\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g,
    u = /[^\w$]+/g,
    v = new RegExp(["\\b" + s.replace(/,/g, "\\b|\\b") + "\\b"].join("|"), "g"),
    w = /^\d[^,]*|,\d[^,]*/g,
    x = /^,+|,+$/g,
    y = /^$|,+/;
    e.openTag = "{{",
    e.closeTag = "}}";
    var z = function(a, b) {
        var c = b.split(":"),
        d = c.shift(),
        e = c.join(":") || "";
        return e && (e = ", " + e),
        "$helpers." + d + "(" + a + e + ")"
    };
    e.parser = function(a) {
        a = a.replace(/^\s/, "");
        var b = a.split(" "),
        c = b.shift(),
        e = b.join(" ");
        switch (c) {
        case "if":
            a = "if(" + e + "){";
            break;
        case "else":
            b = "if" === b.shift() ? " if(" + b.join(" ") + ")": "",
            a = "}else" + b + "{";
            break;
        case "/if":
            a = "}";
            break;
        case "each":
            var f = b[0] || "$data",
            g = b[1] || "as",
            h = b[2] || "$value",
            i = b[3] || "$index",
            j = h + "," + i;
            "as" !== g && (f = "[]"),
            a = "$each(" + f + ",function(" + j + "){";
            break;
        case "/each":
            a = "});";
            break;
        case "echo":
            a = "print(" + e + ");";
            break;
        case "print":
        case "include":
            a = c + "(" + b.join(",") + ");";
            break;
        default:
            if (/^\s*\|\s*[\w\$]/.test(e)) {
                var k = !0;
                0 === a.indexOf("#") && (a = a.substr(1), k = !1);
                for (var l = 0,
                m = a.split("|"), n = m.length, o = m[l++]; n > l; l++) o = z(o, m[l]);
                a = (k ? "=": "=#") + o
            } else a = d.helpers[c] ? "=#" + c + "(" + b.join(",") + ");": "=" + a
        }
        return a
    },
    "function" == typeof define ? define(function() {
        return d
    }) : "undefined" != typeof exports ? module.exports = d: this.template = d
} ();