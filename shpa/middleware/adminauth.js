export default function (context) {
    if (!(context.store.getters.getUser.isAdmin==1)) {
        context.redirect('/login');
    }
} 