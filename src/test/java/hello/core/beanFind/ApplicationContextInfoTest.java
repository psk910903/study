package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //getBeanDefinitionNames : 빈에 등록된 이름들
        //iter + tap : 반복문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); //타입을 모르기 때문에 object 로 꺼내진다.
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }

        //내가 등록한 빈만 보고싶을 때
        //위 코드 복사 붙여넣기 ctrl + d
    }@Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //BeanDefinition : 빈에 대한 메타 데이터 정보들

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                //ROLE_APPLICATION : 내가 앱을 개발하기 위해서 직접 등록한 빈 or 외부 라이브러리
                //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);

            }
        }

    }
}
