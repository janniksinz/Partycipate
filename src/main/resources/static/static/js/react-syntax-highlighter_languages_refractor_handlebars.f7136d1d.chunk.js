(this.webpackJsonpadmin_app=this.webpackJsonpadmin_app||[]).push([[51,81],{264:function(e,n,a){"use strict";function t(e){!function(e){function n(e,n){return"___"+e.toUpperCase()+n+"___"}Object.defineProperties(e.languages["markup-templating"]={},{buildPlaceholders:{value:function(a,t,i,o){if(a.language===t){var r=a.tokenStack=[];a.code=a.code.replace(i,(function(e){if("function"===typeof o&&!o(e))return e;for(var i,s=r.length;-1!==a.code.indexOf(i=n(t,s));)++s;return r[s]=e,i})),a.grammar=e.languages.markup}}},tokenizePlaceholders:{value:function(a,t){if(a.language===t&&a.tokenStack){a.grammar=e.languages[t];var i=0,o=Object.keys(a.tokenStack);!function r(s){for(var l=0;l<s.length&&!(i>=o.length);l++){var u=s[l];if("string"===typeof u||u.content&&"string"===typeof u.content){var c=o[i],p=a.tokenStack[c],g="string"===typeof u?u:u.content,d=n(t,c),f=g.indexOf(d);if(f>-1){++i;var k=g.substring(0,f),b=new e.Token(t,e.tokenize(p,a.grammar),"language-"+t,p),m=g.substring(f+d.length),h=[];k&&h.push.apply(h,r([k])),h.push(b),m&&h.push.apply(h,r([m])),"string"===typeof u?s.splice.apply(s,[l,1].concat(h)):u.content=h}}else u.content&&r(u.content)}return s}(a.tokens)}}}})}(e)}e.exports=t,t.displayName="markupTemplating",t.aliases=[]},536:function(e,n,a){"use strict";var t=a(264);function i(e){e.register(t),function(e){e.languages.handlebars={comment:/\{\{![\s\S]*?\}\}/,delimiter:{pattern:/^\{\{\{?|\}\}\}?$/i,alias:"punctuation"},string:/(["'])(?:\\.|(?!\1)[^\\\r\n])*\1/,number:/\b0x[\dA-Fa-f]+\b|(?:\b\d+\.?\d*|\B\.\d+)(?:[Ee][+-]?\d+)?/,boolean:/\b(?:true|false)\b/,block:{pattern:/^(\s*~?\s*)[#\/]\S+?(?=\s*~?\s*$|\s)/i,lookbehind:!0,alias:"keyword"},brackets:{pattern:/\[[^\]]+\]/,inside:{punctuation:/\[|\]/,variable:/[\s\S]+/}},punctuation:/[!"#%&'()*+,.\/;<=>@\[\\\]^`{|}~]/,variable:/[^!"#%&'()*+,.\/;<=>@\[\\\]^`{|}~\s]+/},e.hooks.add("before-tokenize",(function(n){e.languages["markup-templating"].buildPlaceholders(n,"handlebars",/\{\{\{[\s\S]+?\}\}\}|\{\{[\s\S]+?\}\}/g)})),e.hooks.add("after-tokenize",(function(n){e.languages["markup-templating"].tokenizePlaceholders(n,"handlebars")}))}(e)}e.exports=i,i.displayName="handlebars",i.aliases=[]}}]);
//# sourceMappingURL=react-syntax-highlighter_languages_refractor_handlebars.f7136d1d.chunk.js.map