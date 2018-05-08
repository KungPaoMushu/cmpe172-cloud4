<template>
  <v-card>
    <v-toolbar dark color="primary">
      <span class="title">Salaries</span>
      <v-spacer></v-spacer>
      <v-btn flat icon @click="onDismissed"><v-icon>close</v-icon></v-btn>
    </v-toolbar>
    <v-container>
      <v-layout row wrap>
        <v-flex xs8 offset-xs2>
          <chartjs-line v-if="data !== null" datalabel="Salary" :labels="graphLabels" :data="graphData" :beginzero="true"></chartjs-line>
        </v-flex>
      </v-layout>
    </v-container>
    <v-data-table
      :headers="headers"
      :items="data"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.emp_no }}</td>
        <td class="text-xs-center">{{ props.item.salary }}</td>
        <td class="text-xs-center">{{ props.item.from_date }}</td>
        <td class="text-xs-center">{{ props.item.to_date }}</td>
      </template>
      <template slot="pageText" slot-scope="props">
        Rows {{ props.pageStart }} - {{ props.pageStop }} of {{ props.itemsLength }}
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
  import api from '@/api'
  export default {
    data () {
      return {
        data: null,
        headers: [
          {
            text: 'Employee ID',
            align: 'center',
            value: 'emp_no'
          },
          {
            text: 'Salary',
            align: 'center',
            value: 'salary'
          },
          {
            text: 'Start Date',
            align: 'center',
            value: 'from_date'
          },
          {
            text: 'End Date',
            align: 'center',
            value: 'to_date'
          }
        ]
      }
    },
    props: ['employee'],
    computed: {
      graphLabels () {
        if (this.data === null || this.data === undefined) {
          return []
        }
        return this.data.map(a => a.from_date)
      },
      graphData () {
        if (this.data === null || this.data === undefined) {
          return []
        }
        return this.data.map(a => a.salary)
      }
    },
    methods: {
      async loadData () {
        this.data = await api.getEmployeeSalaries(this.employee)
        console.log(this.data)
      },
      onDismissed () {
        this.$emit('dismissed')
        this.data = null
      }
    },
    watch: {
      employee () {
        if (this.employee !== null && this.employee !== undefined) {
          this.loadData()
        }
      }
    }
  }
</script>