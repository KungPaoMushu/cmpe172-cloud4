<template>
  <v-card>
    <v-card-title>
      Employees
      <v-spacer></v-spacer>
      <v-text-field
        append-icon="search"
        label="Search"
        single-line
        hide-details
        v-model="search"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="employees"
      :search="search"
      :rows-per-page-items="rowsperpage"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center" @click="onClicked(props.item.emp_no)" style="cursor:pointer">{{ props.item.emp_no }}</td>
        <td class="text-xs-center" @click="onClicked(props.item.emp_no)" style="cursor:pointer">{{ props.item.first_name }}</td>
        <td class="text-xs-center" @click="onClicked(props.item.emp_no)" style="cursor:pointer">{{ props.item.last_name }}</td>
        <td class="text-xs-center" @click="onClicked(props.item.emp_no)" style="cursor:pointer">{{ props.item.gender }}</td>
        <td class="text-xs-center" @click="onClicked(props.item.emp_no)" style="cursor:pointer">{{ props.item.birth_date }}</td>
        <td class="text-xs-center" @click="onClicked(props.item.emp_no)" style="cursor:pointer">{{ props.item.hire_date }}</td>
      </template>
      <template slot="pageText" slot-scope="props">
        Rows {{ props.pageStart }} - {{ props.pageStop }} of {{ props.itemsLength }}
      </template>
      <v-alert slot="no-results" :value="true" color="error" icon="warning">
        Your search for "{{ search }}" found no results.
      </v-alert>
    </v-data-table>
    <v-dialog v-model="dialog">
      <employee-salaries :employee="selected" @dismissed="dialog = !dialog"></employee-salaries>
    </v-dialog>
  </v-card>
</template>

<script>
  import api from '@/api'
  import EmployeeSalaries from '@/components/admin/employee_salaries'
  export default {
    data () {
      return {
        search: '',
        rowsperpage: [10, 20, 40, { 'text': 'All', 'value': -1 }],
        headers: [
          {
            text: 'Employee ID',
            align: 'center',
            value: 'emp_no'
          },
          {
            text: 'First name',
            align: 'center',
            value: 'first_name'
          },
          {
            text: 'Last name',
            align: 'center',
            value: 'last_name'
          },
          {
            text: 'Gender',
            align: 'center',
            value: 'gender'
          },
          {
            text: 'Birth date',
            align: 'center',
            value: 'birth_date'
          },
          {
            text: 'Hire date',
            align: 'center',
            value: 'hire_date'
          }
        ],
        employees: [],
        dialog: false,
        selected: null
      }
    },
    methods: {
      async loadEmployees () {
        this.employees = await api.getEmployees()
      },
      onClicked (value) {
        this.selected = value
        this.dialog = !this.dialog
      }
    },
    async created () {
      this.loadEmployees()
    },
    components: {
      'employee-salaries': EmployeeSalaries
    }
  }
</script>