package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Author qinwenlong
 * @Date 2021/11/5
 **/

public class GenericsDemo {


    static class BaseRequest< S extends BaseResponse> {
    }

    static class BaseResponse {
    }

    static class ARequest extends BaseRequest<AResponse> {

    }

    static class BRequest<T extends BaseResponse> {

    }

    static class AResponse extends BaseResponse {

    }

    public  <T extends BaseResponse> T execute(BaseRequest<T> a) {
        return null;
    }

    public static void main(String[] args) {
        GenericsDemo demo = new GenericsDemo();
        ARequest a = new ARequest();
        AResponse execute = demo.execute(a);
    }
}
