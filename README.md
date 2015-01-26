# MineTheWorld
It is a big data time, it is programming time, so let's mine the world by programming.

First of first, to mine the world, we should connect to the world.
As a network time, http is the connection between each other.
So HttpPage is provided to control the http.

As a secure time, it is not so easy to connect to webs not through
browsers because of cookie and ip limit exist.
So Cookie and ProxyManager is provided to solve the problem.

After connecting to web and fetching data from them, it's the turn for data mining.
Not all data is treasure, most of them is rubbish.
To mine treasure out, Reg is provided.

Now the three tools got together, let's mine the world.

# HttpPage
One property and four functions are provided:
  public static String CODE_MODE = "utf8";
  public static String getPageSource(String urlStr);
  public static String getPageSource(String url_str, Map<String,String> headProperty);
  public static byte[] getPageRawSource(String urlStr);
  public static byte[] getPageRawSource(String url_str, Map<String,String> headProperty);
usually we use getPageSource(String urlStr) to get source of http page by giving url string.
The function is simple, only need one parameter. After getting data, it will automatically
transfer to String as CODE_MODE(such as utf-8).
If you don't need string tranfering, you can use function
  public static byte[] getPageRawSource(String urlStr);
The other two functions give you choices to set http head property.
When the http page you access is coded not by utf-8. Before call above functions, CODE_MODE
is need to be set such as gbk.

# Cookie
Cookie is integrated to HttpPage, so you do not need to operate it.

# ProxyManager
ProxyManager is responsible for proxy setting.
Before call setSystemProxy() to change proxy for java program, you need to offer ProxyManager
usable proxys by calling setProxyList(List<String>).
If you hate same first proxy, before you access http page, you may call random() to get a different
first proxy

# IProxy
To get proxy list for ProxyManager, IProxy is designed.
It is a interface containing three functions as follow:
  void setMaxParsePageCount(int page_count);
  void loadProxysFromInternet();
  List<String> getProxys();
We suppose you get proxy list from Internet, so loadProxysFromInternet() is needed.
Maybe there are lots of http page containing proxys, so setMaxParsePageCount(int page_count)
is usable to limit too much proxy mining.
As destination of designing IProxy, getProxys() is a must to get proxy list after loadProxysFromInternet().

# Proxy360dotNet
It is a implement of IProxy.
The proxy list is fetched from http://www.proxy360.net/guonei/
So if you do not have your own IProxy implements, you can try it.

# Reg
Only one function is provided to deal String by the method of REG.
  public static List<String> matches(String reg_str, CharSequence str);
reg_str is a REG String describing data pattern you need,
str is the string you want to find treasure from.
It returns list of string you want.

# Samples
There are some MineTheWorld sample provided in samples directory.
