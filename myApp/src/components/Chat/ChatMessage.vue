<template>
  <div class="chat-message">
    <div class="avatar">
      <img :src="message.avatar" alt="" >
    </div>
    <div class="message-content">
      <div class="username">{{ message.fromUserName }}</div>
      <div class="timestamp">{{ message.timeStamp }}</div>
      <div class="message-text">{{ message.content }}</div>

    </div>
  </div>
</template>

<script setup lang="ts">


const props = defineProps<{
  message:object
}>()


</script>

<style scoped>
.chat-message {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.avatar {
  width: 32px;
  height: 32px;
  margin-right: 8px;

}
img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.message-content {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  /*justify-content: flex-start;*/
  font-size: 14px;
  line-height: 1.5;
}

.username {
  font-weight: bold;
  margin-bottom: 4px;
  color: #6dcff6;
}

.message-text {
  word-wrap: break-word;
  color: #FFFFFF;
}

.timestamp {
  font-size: 12px;
  color: #a5a5a5;

}
</style>
