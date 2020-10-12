import Vuex from 'vuex';
import axios from 'axios';

const createStore = () => {
    return new Vuex.Store({
        state: {
            user: null,
            allUsers: [],
            records: [],
            isLoggedIn: false
        },
        mutations: {
            login(state) {
                state.isLoggedIn = true;
            },
            logout(state) {
                state.isLoggedIn = false;
            },
            setUser(state, payload) {
                state.user = payload;
            },
            setAllUsers(state, payload) {
                state.allUsers = payload;
            },
            setRecords(state, payload) {
                state.records = payload;
            },
            clearAllUsers(state, payload) {
                state.allUsers = [];
            },
            clearUser(state, payload) {
                state.user = null;
            },
            clearRecords(state, payload) {
                state.records = [];
            }
        },
        actions:{
            nuxtServerInit(vuexContext, context) {
                return axios.get('http://localhost:8080/ShpaNow/webapi/record/')
                .then(response => {
                    vuexContext.commit('setRecords', response.data);
                })
                // .then(()=> {
                //    return axios.get('http://localhost:8080/ShpaNow/webapi/user')
                //     .then(response => {
                //         vuexContext.commit('setAllUsers', response.data);
                //     }).catch(e => context.error(e))
                // })
                .catch(e => context.error(e));
            },

            async authenticateUser(vuexContext, user) {
                vuexContext.dispatch('logout');
                let url = 'http://localhost:8080/ShpaNow/webapi/user/validate';
                url = url + '/' + user.email + '/' + user.password;
                const isValid = await axios.post(url).then(response => {
                    if(response.status ==200) {
                        return response.data;
                    }
                    return 0;
                }).catch(e => console.log(e));
                if(isValid == 1) {
                    url = 'http://localhost:8080/ShpaNow/webapi/user/searchuserbyemail/' + user.email;
                    vuexContext.commit('login');
                    const User = await axios.get(url)
                    .then(response => {
                        return response.data;
                    }).catch(e => console.log(e));
                    vuexContext.dispatch('setUser', User);
                    // localStorage.setItem('user', User);
                    // localStorage.setItem('expiresIn', new Date().getTime() + 5*60 * 1000);
                    // vuexContext.dispatch('setLogoutTimer', 5*60*1000);
                }
            },

            // setLogoutTimer(vuexContext, duration) {
            //     setTimeout(() => {
            //         vuexContext.dispatch('logout');
            //     },duration);
            // },

            async registerUser(vuexContext, user){
                vuexContext.dispatch('logout');
                let url = 'http://localhost:8080/ShpaNow/webapi/user/register/';
                let addon = user.name + '/' + user.email + '/' + user.password + '/' + user.phone + '/0';
                url = url + addon;
                const register = await axios.post(url).then(response => {
                    if(response.status == 200) {
                        return response.data;
                    } else {
                        return null;
                    }
                }).catch(e => console.log(e));
                if(register!=null) {
                    url = 'http://localhost:8080/ShpaNow/webapi/user/searchuserbyemail/' + user.email;
                    vuexContext.commit('login');
                    const User = await axios.get(url)
                        .then(response => {
                            return response.data;
                        }).catch(e => console.log(e));
                    // localStorage.setItem('user', User);
                    // localStorage.setItem('expiresIn', new Date().getTime() + 5 * 60 * 1000);
                    vuexContext.dispatch('setUser', User);
                    vuexContext.state.allUsers.push(User);
                }
            },
            setUser(vuexContext, user) {
                vuexContext.commit('setUser', user);
            },

            setAllUsers(vuexContext, users) {
                vuexContext.commit('setAllUsers', users);
            },

            setRecords(vuexContext, records) {
                vuexContext.commit('setRecords', records);
            },

            logout(vuexContext) {
                // if(process.client) {
                //     localStorage.removeItem('user');
                //     localStorage.removeItem('expiresIn');
                // }
                vuexContext.commit('clearUser');
                vuexContext.commit('logout');
            },

            async initRecords(vuexContext) {
                const records = await axios.get('http://localhost:8080/ShpaNow/webapi/record/')
                                .then(response => {
                                    return response.data;
                                }).catch(e=>console.log(e));
                vuexContext.commit('setRecords', records);
            },

            async initUsers(vuexContext) {
                const users = await axios.get('http://localhost:8080/ShpaNow/webapi/user')
                                .then(response => {
                                    return response.data;
                                }).catch(e => console.log(e));
                vuexContext.commit('setAllUsers', users);
            },

            refresh(vuexContext) {
                vuexContext.commit('initUsers');
                vuexContext.commit('initRecords');
            },

            async deleteRecord(vuexContext, pid) {
                const result = await axios.get('http://localhost:8080/ShpaNow/webapi/record/delete/' + pid)
                                .then(response => {
                                    return response.data;
                                }).catch(e => console.log(e));
                vuexContext.dispatch("initRecords");
            },

            async addRecord(vuexContext, record) {
                let url = 'http://localhost:8080/ShpaNow/webapi/record/create/' + vuexContext.state.user.id + '/';
                url = url + record.policyName + '/';
                url = url + record.description + '/';
                url = url + record.state + '/';
                url = url + record.status + '/';
                url = url + record.type + '/';
                url = url + record.jurisdiction + '/';
                url = url + record.source + '/';
                url = url + record.year;

                const result = await axios.post(url).then(response => {
                    return response.data;
                }).catch(e => console.log(e));

                vuexContext.dispatch("initRecords");
            },

            async updateRecord(vuexContext, record) {
                let url = 'http://localhost:8080/ShpaNow/webapi/record/update/' + vuexContext.state.user.id + '/';
                url = url + record.pid + '/';
                url = url + record.policyName + '/';
                url = url + record.description + '/';
                url = url + record.state + '/';
                url = url + record.status + '/';
                url = url + record.type + '/';
                url = url + record.jurisdiction + '/';
                url = url + record.source + '/';
                url = url + record.year;

                const result = await axios.post(url).then(response => {
                    return response.data;
                }).catch(e => console.log(e));

                vuexContext.dispatch("initRecords");
            },

            initAuth(vuexContext) {
                const user = localStorage.getItem('user');
                const expirationDate = localStorage.getItem('expiresIn');

                if (new Date().getTime() > +expirationDate || !user) {
                    vuexContext.dispatch('logout');
                    return;
                }
                vuexContext.dispatch('setLogoutTimer', +expirationDate - new Date().getTime())
                vuexContext.commit('setUser', user);
            },

        },
        getters: {
            isAdmin(state) {
                return state.user.isAdmin;
            },
            isLoggedIn(state) {
                return state.isLoggedIn;
            },
            getUser(state) {
                return state.user;
            },
            getAllUsers(state) {
                return state.allUsers;
            },
            getRecords(state) {
                return state.records;
            }
        }
    });
};

export default createStore;