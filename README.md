# MineTheWorld
It is a big data time, it is programming time, so let's mine the world by programming.<br/>
<br/>
First of first, to mine the world, we should connect to the world.<br/>
As a network time, http is the connection between each other.<br/>
So HttpPage is provided to control the http.<br/>
<br/>
As a secure time, it is not so easy to connect to webs not through<br/>
browsers because of cookie and ip limit exist.<br/>
So Cookie and ProxyManager is provided to solve the problem.<br/>
<br/>
After connecting to web and fetching data from them, it's the turn for data mining.<br/>
Not all data is treasure, most of them is rubbish.<br/>
To mine treasure out, Reg is provided.<br/>
<br/>
Now the three tools got together, let's mine the world.<br/>

# HttpPage
One property and four functions are provided:<br/><br/>
  <code>public static String CODE_MODE = "utf-8";</code><br/>
  <code>public static String getPageSource(String urlStr);</code><br/>
  <code>public static String getPageSource(String url_str, Map<String,String> headProperty);</code><br/>
  <code>public static byte[] getPageRawSource(String urlStr);</code><br/>
  <code>public static byte[] getPageRawSource(String url_str, Map<String,String> headProperty);</code><br/><br/>
usually we use getPageSource(String urlStr) to get source of http page by giving url string.<br/>
The function is simple, only need one parameter. After getting data, it will automatically<br/>
transfer to String as CODE_MODE(such as utf-8).<br/>
If you don't need string tranfering, you can use function<br/>
  <code>public static byte[] getPageRawSource(String urlStr);</code><br/>
The other two functions give you choices to set http head property.<br/>
When the http page you access is coded not by utf-8. Before call above functions, CODE_MODE<br/>
is need to be set such as gbk.<br/><br/>
<b>NOTE: Now HttpPage has ability to identify code mode of http page, and automatically transfer to
the mode, so you do not have to set code mode manually.</b>

# Cookie
Cookie is integrated to HttpPage, so you do not need to operate it.

# ProxyManager
ProxyManager is responsible for proxy setting.<br/>
Before call setSystemProxy() to change proxy for java program, you need to offer ProxyManager<br/>
usable proxys by calling setProxyList(List<String>).<br/>
If you hate same first proxy, before you access http page, you may call random() to get a different<br/>
first proxy

# IProxy
To get proxy list for ProxyManager, IProxy is designed.<br/>
It is a interface containing three functions as follow:<br/><br/>
  <code>void setMaxParsePageCount(int page_count);</code><br/>
  <code>void loadProxysFromInternet();</code><br/>
  <code>List<String> getProxys();</code><br/><br/>
We suppose you get proxy list from Internet, so loadProxysFromInternet() is needed.<br/>
Maybe there are lots of http page containing proxys, so setMaxParsePageCount(int page_count)<br/>
is usable to limit too much proxy mining.<br/>
As destination of designing IProxy, getProxys() is a must to get proxy list after loadProxysFromInternet().<br/>

# Proxy360dotNet
It is a implement of IProxy.<br/>
The proxy list is fetched from http://www.proxy360.net/guonei/<br/>
So if you do not have your own IProxy implements, you can try it.

# Reg
Only one function is provided to deal String by the method of REG.<br/><br/>
  <code>public static List<String> matches(String reg_str, CharSequence str);</code><br/><br/>
reg_str is a REG String describing data pattern you need,<br/>
str is the string you want to find treasure from.<br/>
It returns list of string you want.<br/>

# Writer
tool to write data into file.

# Reader
tool to read data from file.

# Samples
There are some MineTheWorld sample provided in samples directory.
