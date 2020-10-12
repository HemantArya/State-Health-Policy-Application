<template>
  <div class="container">
    <div class="row">
        <h1 style="text-align: center;" class="col-sm-12">Login</h1>
        <div style="width: 30%; margin: 25px auto;">
            <form @submit.prevent="onClick">
                <div class="form-group">
                    <input v-model="user.email" class="form-control" type="email" name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input v-model="user.password" class="form-control" type="password" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <button class="btn btn-large btn-primary btn-block">Log In</button>
                </div>
                <a href="#" @click.prevent="goHome()">Go Back</a>
            </form>
        </div>
    </div>
</div>
</template>

<script>

export default {
    data() {
        return {
            user: {
                email : '',
                password : '',
            }
        }
    },
    layout: 'user',
    methods: {
        goHome() {
            this.$router.push('/');
        },
        onClick() {
            this.$store.dispatch('authenticateUser', this.user)
            .then(() => {
                if(this.$store.getters.isLoggedIn){
                    if(this.$store.getters.getUser.isAdmin) {
                        this.$router.push('/admin');
                    } else {
                        this.$router.push('/user');
                    }
                } else {
                    alert('Wrong Credentials');
                    this.$router.push('/login');
                }
            })
        }
    }
}
</script>

<style scoped>
html,body {
    background-color: white;
}
h1 {
    margin-top: 20vh;
}
</style>