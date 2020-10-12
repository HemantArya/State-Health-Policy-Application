<template>
  <div class="container">
      <div class="row">
          <div class="col-sm-12">
            <p class="alert alert-success">Hi <strong>{{user.name}}</strong>, Welcome to the state health policy application</p>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-12">
            <p class="form-inline"><strong>Search keywords : </strong>  <input class="mx-2 form-control" v-model="filterText" type="text"></p>
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
                      </tr>
                  </tbody>
              </table>
          </div>
        </div>      
  </div>
</template>

<script>
export default {
    
    computed: {
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
    middleware: ['auth'],
    data() {
        return {
            records: this.$store.getters.getRecords,
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
        }
    }
}
</script>

<style scoped>
.alert {
    margin-top: 20px;
}

button {
    float: right;
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