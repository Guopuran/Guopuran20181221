package guopuran.bwie.com.guopuran.model;

import java.util.Map;

import guopuran.bwie.com.guopuran.util.MyCallBack;

public interface Imodel {
    public void requestmodel(String url, Map<String,String> params, Class clazz, MyCallBack callBack);
}
