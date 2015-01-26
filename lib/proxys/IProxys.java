package proxys;

import java.util.List;

public interface IProxys {
	List<String> getProxys();
	void loadProxysFromInternet();
	
	void setMaxParsePageCount(int page_count);
}
