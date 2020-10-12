<template>
  <div class="container">
      <div class="row">
        <h1 style="text-align: center;" class="col-sm-12">Edit Policy</h1>
        <div style="width: 30%; margin: 25px auto;">
            <form @submit.prevent="editRecord">
                <div class="form-group">
                    <input v-model="record.policyName" class="form-control" type="text" name="policyName" placeholder="Policy Name" required>
                </div>
                <div class="form-group">
                    <input v-model="record.description" class="form-control" type="text" name="description" placeholder="Description" required>
                </div>
                <div class="form-group">
                    <input v-model="record.state" class="form-control" type="text" name="state" placeholder="State" required>
                </div>
                <div class="form-group">
                    <input v-model="record.status" class="form-control" type="text" name="status" placeholder="Status" required>
                </div>
                <div class="form-group">
                    <input v-model="record.type" class="form-control" type="text" name="type" placeholder="Type" required>
                </div>
                <div class="form-group">
                    <input v-model="record.jurisdiction" class="form-control" type="text" name="jurisdiction" placeholder="Jurisdiction" required>
                </div>
                <div class="form-group">
                    <input v-model="record.source" class="form-control" type="text" name="source" placeholder="Source" required>
                </div>
                <div class="form-group">
                    <input v-model="record.year" class="form-control" type="number" min="1900" max="2070" name="year" placeholder="Year" required>
                </div>
                <div class="form-group">
                    <button class="btn btn-large btn-primary btn-block">Update Policy</button>
                </div>
            </form>
            <a href="#" @click.prevent="goBack()">Go Back</a>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    // validate({ params }) {
    //     return /^\d+$/.test(params.pid) && this.$store.getters.getRecords.filter(e => e.pid == params.pid)[0];
    // },
    layout: 'user',
    middleware: ['auth', 'adminauth'],
    data() {
        return {
            record: this.$store.getters.getRecords.filter(e => e.pid == this.$route.params.pid)[0]
        }
    },
    methods: {
        goBack() {
            console.log('goBack called');
            this.$router.push('/admin');
        },
        editRecord() {
            console.log('editRecord called');
            this.$store.dispatch('updateRecord', this.record)
            .then(() => {
                this.$router.push('/admin');
            }).catch(e => console.log(e));
        }
    }
}
</script>

<style scoped>
.container {
    margin-top: 20px;
}
</style>