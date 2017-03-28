package com.xy.asyc.test.temp;

import java.util.Map;

public interface IThreadLocalHandler {
	public void set(Map<String, Object> map);

    public Map<String, Object> get();

    public void clear();
}
