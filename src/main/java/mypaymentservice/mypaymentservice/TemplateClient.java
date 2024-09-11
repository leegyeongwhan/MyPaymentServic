package mypaymentservice.mypaymentservice;

// 콜백
interface Callback {
    int execute(final int n);
}

// 템플릿
class Template {
    int workflow(Callback cb) {
        System.out.println("Workflow 시작");
        int num = 100;
        int result = cb.execute(num);
        return result;
    }
}

// 클라이언트
public class TemplateClient {
    public static void main(String[] args) {
        int x = 100;
        int y = 20;

        Template t = new Template();
        int result = t.workflow(new Callback() {
            @Override
            public int execute(final int n) {
                return n * n;
            }
        });
        System.out.println(result); // 100 * 100 = 10000
    }
}
