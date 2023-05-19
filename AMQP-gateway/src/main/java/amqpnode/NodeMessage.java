package amqpnode;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.List;

public class NodeMessage implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
    }

    @Override
    public void containerAckMode(AcknowledgeMode mode) {
        MessageListener.super.containerAckMode(mode);
    }

    @Override
    public boolean isAsyncReplies() {
        return MessageListener.super.isAsyncReplies();
    }

    @Override
    public void onMessageBatch(List<Message> messages) {
        MessageListener.super.onMessageBatch(messages);
    }
}
