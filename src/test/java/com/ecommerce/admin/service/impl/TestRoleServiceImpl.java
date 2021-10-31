package com.ecommerce.admin.service.impl;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestRoleServiceImpl {

//	@Test
//	public void testMappingRoleToRoleDTO() {
//		List mockList = Mockito.mock(List.class);
//		
//		Mockito.when(mockList.length()).thenReturn(3);
//		
//		Assert.assertEquals(2, mockList.length());
//	}
    @Spy
    List<String> list = new ArrayList<>();

    @Test
    public void testSpy() {
        list.add("one");
        list.add("two");
        // show the list items
        System.out.println(list);

        Mockito.verify(list).add("one");
        Mockito.verify(list).add("two");

        // @Spy thực sự gọi hàm .add của List nên nó có size là 2 mà không cần giả lập
        Assert.assertEquals(2, list.size());

        // Vẫn có thể làm giả thông tin gọi hàm với @Spy
        Mockito.when(list.size()).thenReturn(100);

        Assert.assertEquals(100, list.size());
    }
}
