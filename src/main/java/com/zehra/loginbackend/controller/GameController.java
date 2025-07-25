package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;
import org.springframework.stereotype.Controller;

@Controller
@CrossOrigin(origins = "*")
public class GameController {

    // /app/join mesajı geldiğinde tetiklenir, tüm abonelere /topic/players üzerinden yayınlanır
    @MessageMapping("/join")
    @SendTo("/topic/players")
    public Player handleJoin(Player player) {
        player.setInitial(HtmlUtils.htmlEscape(player.getName().substring(0, 1).toUpperCase()));
        return player;
    }

    // /app/select mesajı geldiğinde tetiklenir
    @MessageMapping("/select")
    @SendTo("/topic/players")
    public Player handleCardSelection(Player player) {
        return player;
    }
}
