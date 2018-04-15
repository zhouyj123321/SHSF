package org.zyj.shsf.client.http;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.zyj.shsf.client.http.param.AbstractRequestParam;
import org.zyj.shsf.client.http.param.DefaultJSONRequestParam;
import org.zyj.shsf.client.http.param.JsonParam;
import org.zyj.shsf.client.http.response.AbstractHttpResponseType;
import org.zyj.shsf.client.http.response.DefaultJSONResponse;
import org.zyj.shsf.http.common.RequestType;

public class ShsfHttpJsonDataRequestTest {

	@Test
	public void test() {
  // URL列表数组
    	
        String[] urisToGet = {
                "http://localhost:9080/testWeb/index.html",
                "http://localhost:9080/testWeb/index.html",
                "http://localhost:9080/testWeb/index.html",
                "http://localhost:9080/testWeb/index.html" };

        long start = System.currentTimeMillis();
        try {
            int pagecount = urisToGet.length;
            ExecutorService executors = Executors.newFixedThreadPool(pagecount);
            CountDownLatch countDownLatch = new CountDownLatch(pagecount);
            for (int i = 0; i < pagecount; i++) {
//                HttpGet httpget = new HttpGet(urisToGet[i]);
//                config(httpget);
                // 启动线程抓取
                executors
                        .execute(new GetRunnable(urisToGet[i], countDownLatch));
            }
            countDownLatch.await();
            executors.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程" + Thread.currentThread().getName() + ","
                    + System.currentTimeMillis() + ", 所有线程已完成，开始进入下一步！");
        }

        long end = System.currentTimeMillis();
        System.out.println("consume -> " + (end - start));
    }

    static class GetRunnable implements Runnable {
        private CountDownLatch countDownLatch;
        private String url;

        public GetRunnable(String url, CountDownLatch countDownLatch) {
            this.url = url;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
//            	HttpClientUtil.get(url);
            	AbstractRequestParam<JsonParam> param = new DefaultJSONRequestParam(url,RequestType.GET,null);
            	AbstractHttpResponseType art = new DefaultJSONResponse();
                System.out.println(HttpClientUtil.get(param,art).getResult());
            } finally {
                countDownLatch.countDown();
            }
        }
    }
	
	

}
