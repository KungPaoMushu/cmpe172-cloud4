<template>
  <v-card>
    <v-card-title>
      Departments
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
      :items="departments"
      :search="search"
      :rows-per-page-items="rowsperpage"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.dept_no }}</td>
        <td class="text-xs-center">{{ props.item.dept_name }}</td>
      </template>
      <template slot="pageText" slot-scope="props">
        Rows {{ props.pageStart }} - {{ props.pageStop }} of {{ props.itemsLength }}
      </template>
      <v-alert slot="no-results" :value="true" color="error" icon="warning">
        Your search for "{{ search }}" found no results.
      </v-alert>
    </v-data-table>
  </v-card>
</template>

<script>
  import api from '@/api'
  export default {
    data () {
      return {
        search: '',
        rowsperpage: [10, 20, 40, { 'text': 'All', 'value': -1 }],
        headers: [
          {
            text: 'Department ID',
            align: 'center',
            value: 'dept_no'
          },
          {
            text: 'Department name',
            align: 'center',
            value: 'dept_name'
          }
        ],
        departments: []
      }
    },
    methods: {
      async loadDepartments () {
        this.departments = await api.getDepartments()
      }
    },
    async created () {
      this.loadDepartments()
    }
  }
</script>