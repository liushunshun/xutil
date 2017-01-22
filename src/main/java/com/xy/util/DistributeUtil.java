package com.xy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DistributeUtil {
	
	public static void splitParallelCompute(List<Long> taskList, int taskUnit) {
        if (taskList == null || taskList.isEmpty()) {
            return ;
        }
        if (taskUnit <= 0) {
            taskUnit = 1;
        }
        Map<Integer, List<Long>> groupTaskMapList = new HashMap<Integer, List<Long>>();
        // 分成几组
        int taskTotalSize = taskList.size();
        int groupCount = taskTotalSize % taskUnit == 0 ? taskTotalSize / taskUnit : taskTotalSize / taskUnit + 1;

        Long func = null;
        List<Long> groupList = null;
        for (int i = 0; i < groupCount; i++) {
            int groupIndex = i * taskUnit;
            groupList = new ArrayList<Long>();
            int j = 0;
            while (j < taskUnit && groupIndex < taskTotalSize) {
                func = taskList.get(groupIndex++);
                if (func == null) {
                    continue;
                }
                groupList.add(func);
                j++;
            }
            groupTaskMapList.put(i + 1, groupList);
        }

        // 分批次调用
        for (Entry<Integer, List<Long>> entry : groupTaskMapList.entrySet()) {
            System.out.println(entry.getValue());
        }

    }
public static void main(String[] args) {
	List<Long> ids = Arrays.asList(1L,3L,4L,5L,7L,9L,12L,23L,34L);
	splitParallelCompute(ids,1);
}
	
}

