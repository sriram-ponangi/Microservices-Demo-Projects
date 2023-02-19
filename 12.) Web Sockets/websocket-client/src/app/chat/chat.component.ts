import { Component } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  title = 'Chatting Approach';

  chats: string[] = [];
  disabled = false;
  name: string;
  private stompClient = null;

  constructor() { }

  setConnected(connected: boolean) {
    this.disabled = connected;

    if (connected) {
      this.chats = [];
    }
    console.log(' this.disabled ', this.disabled);
  }

  connect() {
    const socket = new SockJS('http://localhost:9999/socket');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/user/queue/chat', function (data) {
        _this.showGreeting(data.body);
      });
    });
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  sendName() {
    this.stompClient.send(
      '/app/chat',
      {},
      this.name // JSON.stringify({ 'message': this.name })
    );
  }

  showGreeting(message) {
    this.chats.push(message);
  }

}
