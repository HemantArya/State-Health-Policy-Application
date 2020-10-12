<template>
  <div class="container">
      <div class="row">
        <h1 style="text-align: center;" class="col-sm-12">Add New Policy</h1>
        <div style="width: 30%; margin: 25px auto;">
            <form @submit.prevent="addRecord">
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
                    <button class="btn btn-large btn-primary btn-block">Add Policy</button>
                </div>
                <a href="#" @click.prevent="goBack()">Go Back</a>
            </form>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    layout: 'user',
    middleware: ['auth', 'adminauth'],
    data() {
        return {
            record: {
                policyName: '',
                description: '',
                state: '',
                status: '',
                type: '',
                jurisdiction: '',
                source: '',
                year: ''
            }
        }
    },
    methods: {
        goBack() {
            this.$router.push('/admin');
        },
        addRecord() {
            this.$store.dispatch('addRecord', this.record)
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