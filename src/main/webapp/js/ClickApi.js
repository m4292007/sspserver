/*
 * SanomaClicksApi
 * v0.0.1
 * 2017-05-09
 **/
var SanomaClicksApi = function(a) {
    function b(a) {
        return a.substr(0, j.length) === j
    }

    function c(b) {
        void 0 !== a.mraid ? a.mraid.open(b) : a.open(b, "_blank")
    }
    var d = "click",
        e = d + "prefix",
        f = d + "tag",
        g = "videopreurl",
        h = "",
        i = function(a) {
            if (a === h) return {};
            for (var b = {}, c = 0; c < a.length; ++c) {
                var d = a[c].split(/(.*?)=(.*)/);
                1 == d.length ? b[d[1]] = h : b[d[1]] = decodeURIComponent(d[2].replace(/\+/g, " "))
            }
            return b
        }(a.location.search.substr(1).split("&")),
        j = i[e] || h;
    return {
        getClickPrefix: function() {
            return j
        },
        getClickTagUrl: function(a) {
            return b(i[a] || h) ? i[a] || h : i[a] ? j + i[a] : h
        },
        getClickTag: function() {
            return b(i[f] || h) ? i[f] || h : i[f] ? j + i[f] : h
        },
        getVideoPreUrl: function() {
            return i[g] ? j + i[g] : h
        },
        openClickUrl: function(a) {
            void 0 === a && (a = i[f] || h), b(a) || (a = j + a), c(a)
        },
        openClickTag: function(a) {
            c(this.getClickTagUrl(a))
        }
    }
}(this);