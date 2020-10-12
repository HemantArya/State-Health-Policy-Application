<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="navbar-header mx-4">
          <nuxt-link to="/" class="navbar-brand">SHPA</nuxt-link>
      </div>
      <div class="nav-item active"  v-if="user && user.isAdmin == 0">
        <nuxt-link class="nav-link" to="/user">Role: User <span class="sr-only">(current)</span></nuxt-link>
      </div>
      <div class="nav-item active" v-if="user && user.isAdmin == 1">
        <nuxt-link class="nav-link" to="/admin">Role: Admin <span class="sr-only">(current)</span></nuxt-link>
      </div>
      <ul class="collapse navbar-collapse nav justify-content-end mx-4">
              <li class="nav-link nav-item" v-if="user==null"><nuxt-link to="/login">Login</nuxt-link></li>
              <li class="nav-link nav-item" v-if="user==null"><nuxt-link to="/register">Sign Up</nuxt-link></li>
              <li class="nav-link nav-item" v-if="user!=null"><a href="#" @click.prevent>Signed In As {{user.name}}</a></li>
              <li class="nav-link nav-item" v-if="user!=null"><a href="#" @click.prevent="logout">Logout</a></li>
      </ul>
    </nav>
    <Nuxt />
  </div>
</template>

<script>
export default {
  computed: {
    user() {
      return this.$store.getters.getUser
    }
  },
  methods: {
    logout() {
            this.$store.dispatch('logout')
            .then(() => {
                this.$router.push('/');
            }).catch(e=> console.log(e));
        }
  }
}
</script>

<style>
html,body{
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}

a, a:hover {
  text-decoration: none;
}

ul * {
  color: #fff;
}

</style>
