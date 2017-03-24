package com.xy.asyc;

import java.util.Map;

public interface IThreadLocalHandler {
	public void set(Map<String, Object> map);

    public Map<String, Object> get();

    public void clear();
}
