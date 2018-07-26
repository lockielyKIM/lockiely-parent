package org.lockiely.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class EmailApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof UserRegisterEvent){
            System.out.println("邮件服务接到通知，给用户："+ event.getSource().toString() +"发送邮件");
        }
    }
}
