
Ajax = {
    request : function(config) {
        if (!config) {
            config = {};
        }
        var method = config.method ? config.method : 'POST';
        var url = config.url;
        var params = config.params ? config.params : '';

        if (!url) {
            alert('需要url');
            return;
        }

        /* Create a new XMLHttpRequest object to talk to the Web server */
        var xmlHttp = false;
        /*@cc_on @*/
        /*@if (@_jscript_version >= 5)
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e2) {
                xmlHttp = false;
            }
        }
        @end @*/
        if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
          xmlHttp = new XMLHttpRequest();
        }

        xmlHttp.open(method, url, true);
        var me = this;
        var success = config.success ? config.success : function(){};
        var failure = config.failure ? config.failure : function(){};
        xmlHttp.onreadystatechange = function() {
            me.processResponse(xmlHttp, success, failure);
        }
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send(params);
    },

    processResponse : function(xmlHttp, success, failure) {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                success.call(window, xmlHttp);
            } else {
                failure.call(window, xmlHttp);
            }
        }
    }
};

