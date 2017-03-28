package com.xy.asyc.test.temp;

public class DefaultAsynWork extends WorkContext implements AsynWork{

	private static final long serialVersionUID = 5025664376598378770L;

	private Object handlerObject;

    private String method;

    private Class<?>[] paramTypes;

    private Object[] paramsValues;

    private JobPriority jobPriority = JobPriority.NORM;
    
    public DefaultAsynWork(Object handlerObject, String method) {
        this(handlerObject, method, null, null);
    }

    public DefaultAsynWork(Object handlerObject, String method, Class<?>[] paramTypes, Object[] paramsValues) {
        this(handlerObject, method, paramTypes, paramsValues, null);
    }

    public DefaultAsynWork(Object handlerObject, String method, Class<?>[] paramTypes, Object[] paramsValues,
            JobPriority jobPriority) {
        if (handlerObject == null) {
            throw new Asyn4jException("Target Object is null");
        }
        if (method == null || method.trim().length() == 0) {
            throw new Asyn4jException("Target method is null");
        }

        this.handlerObject = handlerObject;
        this.method = method;
        this.paramTypes = paramTypes;
        this.paramsValues = paramsValues;
        if (jobPriority != null) {
            this.jobPriority = jobPriority;
        }
    }
    
	@Override
	public String getThreadName() {
		String className = handlerObject.getClass().getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(className).append("-").append(this.method);
        return sb.toString();
	}

	@Override
	public AsynCallback call() throws Exception {
		invoke(handlerObject, method, paramTypes, paramsValues);
		return null;
	}

	@Override
	public int getPriority() {
		return this.jobPriority.getValue();
	}
}
