<template>
  <v-card>
    <v-card-title>
      <span class="title">Salaries</span>
      <v-spacer></v-spacer>
      <v-btn flat icon @click="onDismissed">x</v-btn>
    </v-card-title>
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
  import LineChart from '@/components/admin/line_chart.js'
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
      chartData () {
        var data = {
          labels: (this.data === null || this.data === undefined) ? [] : this.data.map(a => a.from_date),
          datasets: [
            {
              label: 'Salaries',
              backgroundColor: '#f87979',
              data: (this.data === null || this.data === undefined) ? [] : this.data.map(a => a.salaries)
            }
          ]
        }
        return data
      },
      chartOptions () {
        return { responsive: false, maintainAspectRatio: false }
      }
    },
    methods: {
      async loadData () {
        this.data = await api.getEmployeeSalaries(this.employee)
        console.log(this.data)
      },
      onDismissed () {
        this.$emit('dismissed')
      }
    },
    watch: {
      employee () {
        if (this.employee !== null && this.employee !== undefined) {
          this.loadData()
        }
      }
    },
    components: {
      'line-chart': LineChart
    }
  }
</script>