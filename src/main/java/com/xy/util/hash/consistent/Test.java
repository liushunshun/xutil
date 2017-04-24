package com.xy.util.hash.consistent;

import java.util.ArrayList;
import java.util.List;


public class Test {
	public static void main(String[] args) {
		List<PhysicalNode> list = new ArrayList<PhysicalNode>();
		
		list.add(new PhysicalNode("www.baidu.com", "192.168.1.1", 80));
		list.add(new PhysicalNode("www.baidu.com", "192.168.1.2", 80));
		list.add(new PhysicalNode("www.baidu.com", "192.168.1.3", 80));
		list.add(new PhysicalNode("www.baidu.com", "192.168.1.4", 80));
		
		ConsistentHashRouter router = new ConsistentHashRouter(list, 1024);
		
		int name1 = 0;
		int name2 = 0;
		int name3 = 0;
		int name4 = 0;
		int total = 100000;
		
		for(int i=0;i<total;i++){
			PhysicalNode node = router.getNode(i+"");
			if("192.168.1.1".equals(node.getIp())){
				name1 ++;
			}else if("192.168.1.2".equals(node.getIp())){
				name2 ++;
			}else if("192.168.1.3".equals(node.getIp())){
				name3 ++;
			}else if("192.168.1.4".equals(node.getIp())){
				name4 ++;
			}
			
		}
		System.out.println((double)name1/total);
		System.out.println((double)name2/total);
		System.out.println((double)name3/total);
		System.out.println((double)name4/total);
	}
}
