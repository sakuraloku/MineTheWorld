# MineTheWorld
It is a big data time, it is programming time, so let's mine the world by programming.<br/>
<br/>
First of first, to mine the world, we should connect to the world.<br/>
As a network time, http is the connection between each other.<br/>
So <code>HttpPage</code> is provided to control the http.<br/>
<br/>
As a secure time, it is not so easy to connect to webs not through
browsers because of cookie and ip limit exist.<br/>
So <code>Cookie</code> and <code>ProxyManager</code> is provided to solve the problem.<br/>
<br/>
After connecting to web and fetching data from them, it's the turn for data mining.<br/>
Not all data is treasure, most of them is rubbish.<br/>
To mine treasure out, <code>Reg</code> is provided.<br/>
<br/>
Now the three tools got together, let's mine the world.<br/>

# HttpPage
One property and four functions are provided:<br/><br/>
  <code>public static String CODE_MODE = "utf-8";</code><br/>
  <code>public static String getPageSource(String urlStr);</code><br/>
  <code>public static String getPageSource(String url_str, Map<String,String> headProperty);</code><br/>
  <code>public static byte[] getPageRawSource(String urlStr);</code><br/>
  <code>public static byte[] getPageRawSource(String url_str, Map<String,String> headProperty);</code><br/><br/>
usually we use <code>getPageSource(String urlStr)</code> to get source of http page by giving url string.<br/>
The function is simple, only need one parameter. After getting data, it will automatically
transfer to <code>String</code> as <code>CODE_MODE</code>(such as utf-8).<br/>
If you don't need string tranfering, you can use function<br/><br/>
  <code>public static byte[] getPageRawSource(String urlStr);</code><br/><br/>
The other two functions give you choices to set http head property.<br/>
When the http page you access is coded not by utf-8. Before call above functions, <code>CODE_MODE</code>
is need to be set such as gbk.<br/><br/>
<b>NOTE: Now <code>HttpPage</code> has ability to identify code mode of http page, and automatically transfer to
the mode, so you do not have to set code mode manually.</b>

# Cookie
<code>Cookie</code> is integrated to <code>HttpPage</code>, so you do not need to operate it.

# ProxyManager
<code>ProxyManager</code> is responsible for proxy setting.<br/>
Before call <code>setSystemProxy()</code> to change proxy for java program, you need to offer <code>ProxyManager</code> usable proxys by calling <code>setProxyList(List<String>)</code>.<br/>
If you hate same first proxy, before you access http page, you may call <code>random()</code> to get a different
first proxy

# IProxy
To get proxy list for <code>ProxyManager</code>, <code>IProxy</code> is designed.<br/>
It is a interface containing three functions as follow:<br/><br/>
  <code>void setMaxParsePageCount(int page_count);</code><br/>
  <code>void loadProxysFromInternet();</code><br/>
  <code>List<String> getProxys();</code><br/><br/>
We suppose you get proxy list from Internet, so <code>loadProxysFromInternet()</code> is needed.<br/>
Maybe there are lots of http page containing proxys, so <code>setMaxParsePageCount(int page_count)</code>
is usable to limit too much proxy mining.<br/>
As destination of designing <code>IProxy</code>, <code>getProxys()</code> is a must to get proxy list after <code>loadProxysFromInternet()</code>.<br/>

# Proxy360dotNet
It is a implement of <code>IProxy</code>.<br/>
The proxy list is fetched from http://www.proxy360.net/guonei/
So if you do not have your own <code>IProxy</code> implements, you can try it.

# Reg
Only one function is provided to deal <code>String</code> by the method of <code>REG</code>.<br/><br/>
  <code>public static List<String> matches(String reg_str, CharSequence str);</code><br/><br/>
<code>reg_str</code> is a <code>REG String</code> describing data pattern you need,
<code>str</code> is the string you want to find treasure from.<br/>
It returns list of string you want.<br/>

# Writer
tool to write data into file.

# Reader
tool to read data from file.

# Samples
There are some <code>MineTheWorld</code> sample provided in samples directory.
