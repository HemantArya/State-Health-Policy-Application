<template>
  <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <p class="alert alert-success">Hi <strong>{{user.name}}</strong>, Welcome to the state health policy application</p>
            </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <p class="form-inline"><strong>Search keywords : </strong>  <input class="mx-2 form-control" v-model="filterText" type="text"> <span><button class="btn btn-primary" @click="addRecord">Add new policy</button></span> </p>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
              <table class="table">
                  <thead>
                      <tr>
                        <th>S.No.</th>
                        <th>Policy Name</th>
                        <th>Description</th>
                        <th>State</th>
                        <th>Status</th>
                        <th>Type</th>
                        <th>Jurisdiction</th>
                        <th>Source</th>
                        <th>Year</th>
                        <th>Edit</th>
                        <th>Delete</th>
                      </tr>
                  </thead>
                  <tbody>
                      <tr v-for="(record,index) in filteredRecords" :key="record.pid">
                          <td>{{ index+1 }}</td>
                          <td>{{ record.policyName }}</td>
                          <td>{{ record.description }}</td>
                          <td>{{ record.state }}</td>
                          <td>{{ record.status }}</td>
                          <td>{{ record.type }}</td>
                          <td>{{ record.jurisdiction }}</td>
                          <td>{{ record.source }}</td>
                          <td>{{ record.year }}</td>
                          <td><svg @click="editRecord(record.pid)" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="edit" class="svg-inline--fa fa-edit fa-w-18" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="currentColor" d="M402.6 83.2l90.2 90.2c3.8 3.8 3.8 10 0 13.8L274.4 405.6l-92.8 10.3c-12.4 1.4-22.9-9.1-21.5-21.5l10.3-92.8L388.8 83.2c3.8-3.8 10-3.8 13.8 0zm162-22.9l-48.8-48.8c-15.2-15.2-39.9-15.2-55.2 0l-35.4 35.4c-3.8 3.8-3.8 10 0 13.8l90.2 90.2c3.8 3.8 10 3.8 13.8 0l35.4-35.4c15.2-15.3 15.2-40 0-55.2zM384 346.2V448H64V128h229.8c3.2 0 6.2-1.3 8.5-3.5l40-40c7.6-7.6 2.2-20.5-8.5-20.5H48C21.5 64 0 85.5 0 112v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V306.2c0-10.7-12.9-16-20.5-8.5l-40 40c-2.2 2.3-3.5 5.3-3.5 8.5z"></path></svg></td>
                          <td><svg @click="deleteRecord(record.pid)" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="trash-alt" class="svg-inline--fa fa-trash-alt fa-w-14" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="currentColor" d="M32 464a48 48 0 0 0 48 48h288a48 48 0 0 0 48-48V128H32zm272-256a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zm-96 0a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zm-96 0a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zM432 32H312l-9.4-18.7A24 24 0 0 0 281.1 0H166.8a23.72 23.72 0 0 0-21.4 13.3L136 32H16A16 16 0 0 0 0 48v32a16 16 0 0 0 16 16h416a16 16 0 0 0 16-16V48a16 16 0 0 0-16-16z"></path></svg></td>
                      </tr>
                  </tbody>
              </table>
          </div>
        </div>      
  </div>
</template>

<script>
export default {
    middleware: ['auth', 'adminauth'],
    computed: {
        records() {
            return this.$store.getters.getRecords;
        },
        filteredRecords() {
            return this.records.filter((element)=>{
                let res = element.policyName.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.policyName.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.description.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.status.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.state.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.jurisdiction.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.source.toLowerCase().match(this.filterText.toLowerCase()) ||
                element.year.toString().toLowerCase().match(this.filterText.toLowerCase());
                return res;
            });
        }
    },
    layout: 'user',
    data() {
        return {
            user: this.$store.getters.getUser,
            filterText: ''
        }
    },
    methods: {
        logout() {
            this.$store.dispatch('logout')
            .then(() => {
                this.$router.push('/');
            }).catch(e=> console.log(e));
        },
        editRecord(pid) {
            this.$router.push("/admin/editpolicy/"+pid);
        },
        deleteRecord(pid) {
            this.$store.dispatch('deleteRecord', pid)
            .catch(e => console.log(e));
        },
        addRecord() {
          this.$router.push('/admin/newpolicy');
        }
    }
}
</script>

<style scoped>
.alert {
    margin-top: 20px;
}

span {
    float: right;
}

svg {
    justify-content: center;
    height: 20px;
    cursor: pointer;
}

table {
  max-width: 100%;
}

tr:nth-child(odd) {
  background-color: #eee;
}

th {
  background-color: #555;
  color: #fff;
}

th,
td {
  text-align: left;
  padding: 0.5em 1em;
}
</style>