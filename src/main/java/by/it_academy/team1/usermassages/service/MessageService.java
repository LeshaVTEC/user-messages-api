package by.it_academy.team1.usermassages.service;

import by.it_academy.team1.usermassages.core.dto.MessageDto;
import by.it_academy.team1.usermassages.core.entity.Message;
import by.it_academy.team1.usermassages.core.entity.User;
import by.it_academy.team1.usermassages.dao.MessageDao;
import by.it_academy.team1.usermassages.dao.api.IMessageDao;
import by.it_academy.team1.usermassages.service.api.IMessageService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private IMessageDao dao;

    public MessageService(IMessageDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Message> get() {
        return this.dao.get();
    }

    @Override
    public List<Message> getMessagesOfUser(String username) {
        return this.dao.get().stream()
                .filter(message -> message.getUsernameFrom().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public void sendMessage(MessageDto message) {
        Message entity = new Message();
        entity.setUsernameTo(message.getUsernameTo());
        entity.setUsernameFrom(message.getUsernameFrom());
        entity.setText(message.getText());
        entity.setSentDate(message.getSentDate()); // Установите дату/время отправки

        this.dao.save(entity); // Предполагается, что у вас есть метод сохранения сообщения в DAO
    }
}
