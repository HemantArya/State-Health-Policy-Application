export default function (context) {
    if (!(context.store.getters.getUser.isAdmin == 0)) {
        context.redirect('/login');
    }
} 