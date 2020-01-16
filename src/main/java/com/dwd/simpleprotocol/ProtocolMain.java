package com.dwd.simpleprotocol;

import com.dwd.SimpleProtocols;
import com.google.protobuf.*;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/9/9 下午2:54
 */
public class ProtocolMain {
    public static void main(String[] args) throws ServiceException {
//


       SimpleProtocols.SearchService.BlockingInterface blockingInterface  =  SimpleProtocols.SearchService.newBlockingStub(new BlockingRpcChannel() {
            public Message callBlockingMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request, Message responsePrototype) throws ServiceException {
                if("Search".equals(method.getName())){
                    SimpleProtocols.SearchRequest r = (SimpleProtocols.SearchRequest) request;
                    SimpleProtocols.SearchResponse res = (SimpleProtocols.SearchResponse)responsePrototype;
                    return res.toBuilder().addResult(SimpleProtocols.Result.newBuilder()
                    .setUrl("url"+r.getQuery())
                            .setTitle("title"+r.getPageNumber())
                            .addSnippets("snippets" + r.getResultPerPage())
                    ).build();
                }else{
                    return null;
                }
            }
        });
        Descriptors.MethodDescriptor descriptor = SimpleProtocols.SearchService.getDescriptor().findMethodByName("Search");
        BlockingService service = SimpleProtocols.SearchService.newReflectiveBlockingService(blockingInterface);

        Message message = SimpleProtocols.SearchRequest.newBuilder().setQuery("query")
                .setPageNumber(2)
                .setResultPerPage(20).build();
        Message  res = service.callBlockingMethod(descriptor,null, message);
        System.out.println(res.toString());
    }
}
