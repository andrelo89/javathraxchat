<%@ page contentType="text/html; charset=utf-8" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>DConsult</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="bg" />
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script>$.extend({URLEncode:function(c){var o='';var x=0;c=c.toString();var r=/(^[a-zA-Z0-9_.]*)/;
  while(x<c.length){var m=r.exec(c.substr(x));
    if(m!=null && m.length>1 && m[1]!=''){o+=m[1];x+=m[1].length;
    }else{if(c[x]==' ')o+='+';else{var d=c.charCodeAt(x);var h=d.toString(16);
    o+='%'+(h.length<2?'0':'')+h.toUpperCase();}x++;}}return o;},
URLDecode:function(s){var o=s;var binVal,t;var r=/(%[^%]{2})/;
  while((m=r.exec(o))!=null && m.length>1 && m[1]!=''){b=parseInt(m[1].substr(1),16);
  t=String.fromCharCode(b);o=o.replace(m[1],t);}return o;}
});

  String.prototype.startsWith = function(str) 
  {return (this.match("^"+str)==str)};


  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-8850356-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  </script>
</head>

<body>
    <div id="main"><div id="main2">
            <div id="corner1">&nbsp;</div>
            <div id="corner2">&nbsp;</div>
            <div id="header">
                <h1><a href="/index.jsp">D<span>consult</span>ing</a></h1>
                <div id="menu">
                    <ul>
                        <li><a href="/index.jsp">start</a></li>
                        <li><a href="/services.jsp">services</a></li>
                        <li><a href="/products.jsp">products</a></li>
                        <li><a href="/about.jsp">about us</a></li>
                    </ul>
                </div>
            </div>    
            