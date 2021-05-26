package javatesttask.task.utils.handler;

import org.springframework.stereotype.Component;

@Component
public class WordQueryHandler implements CaseHandler {

    @Override
    public String handle(String str) {

        StringBuilder stringBuilder = new StringBuilder(str.toLowerCase());
        stringBuilder.replace(0, 1, Character.toString(str.charAt(0)).toUpperCase());

        return stringBuilder.toString();
    }

}
