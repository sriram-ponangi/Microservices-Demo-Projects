import { Component } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
@Component({
  selector: 'app-broadcast',
  templateUrl: './broadcast.component.html',
  styleUrls: ['./broadcast.component.css']
})
export class BroadcastComponent {

  title = 'Broadcast Approach';

  messages: string[] = [];
  disabled: boolean;
  name: string;
  private stompClient = null;

  constructor() { }

  setConnected(connected: boolean) {
    this.disabled = connected;

    if (connected) {
      this.messages = [];
    }
  }

  connect() {
    const socket = new SockJS('http://localhost:9999/socket');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/notification', function (hello) {
        console.log('got message from /topic/notification');
        _this.showGreeting(JSON.parse(hello.body).message);
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
      '/app/notify',
      {},
      JSON.stringify({ 'message': this.name })
    );
  }

  showGreeting(message) {
    this.messages.push(message);
  }
}
