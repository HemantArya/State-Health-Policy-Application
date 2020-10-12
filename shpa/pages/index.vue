<template>
  <div class="container">
    <div id="landing-header">
        <h1>Welcome to State Health Policy Application!</h1>
        <div class="btn-container d-flex justify-content-around">
          <button class="btn btn-lg btn-success" @click="onLogin">Login</button>
          <button class="btn btn-lg btn-primary" @click="onRegister">Register</button>
        </div>
    </div>
    <ul class="slideshow">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
  </div>
</template>

<script>
export default {
  layout: "landing",
  methods: {
    onLogin() {
      this.$router.push("/login");
    },
    onRegister() {
      this.$router.push("/register");
    }
  }
}
</script>

<style scoped>


body {
  background-color: #000;
}

#landing-header {
  z-index: 1;
  position: relative;
  text-align: center;
  padding-top: 35vh;
}

#landing-header h1 {
  color: #fff;
  text-shadow: 2px 2px 2px #000;
}

.slideshow { 
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 0;
  list-style: none;
  margin: 0;
  padding: 0;
}

.slideshow li { 
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-size: cover;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  opacity: 0;
  z-index: 0;
  animation: imageAnimation 50s linear infinite; 
}

.slideshow li:nth-child(1) { 
  background-image: url(https://images.unsplash.com/photo-1521587760476-6c12a4b040da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80) 
}
.slideshow li:nth-child(2) { 
  background-image: url(https://images.unsplash.com/photo-1492551557933-34265f7af79e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80);
  animation-delay: 10s; 
}
.slideshow li:nth-child(3) { 
  background-image: url(https://images.unsplash.com/photo-1554195576-93a79d300165?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80);
  animation-delay: 20s; 
}
.slideshow li:nth-child(4) { 
  background-image: url(https://images.unsplash.com/photo-1518397463145-e9a90f524faa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80);
  animation-delay: 30s; 
}
.slideshow li:nth-child(5) { 
  background-image: url(https://images.unsplash.com/photo-1542621323-be453184db76?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80);
  animation-delay: 40s; 
}

@keyframes imageAnimation { 
  0% { 
    opacity: 0; 
    animation-timing-function: ease-in;
  }
  10% {
    opacity: 1;
    animation-timing-function: ease-out;
  }
  20% {
    opacity: 1
  }
  30% {
    opacity: 0
  }
}

.no-cssanimations .slideshow li {
	opacity: 1;
}

.btn-container {
  padding: 20px;
  margin: 20px 25%;
}

button {
  margin: 0px 10px;
  width: 40%;
}
</style>
