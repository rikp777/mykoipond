import apiService from '../../services/api.service'

const apiUrl = "/parasites"

// Initial State
const state = {
    error: null,
    parasites: [],
    parasite: {},
    isLoading: true,
    page: {
        total: 0
    }
}

// Getters
const getters = {
    parasites(state) {
        return state.parasites
    },
    parasite(state) {
        return state.parasite
    },
    parasitePage(state){
        return state.page
    },
    parasiteIsLoading(state){
        return state.isLoading
    },
    parasiteError(state){
        return state.error
    }
}

// Actions
const actions = {
    getAllParasites(context, params) {
        context.commit("startLoading")
        return apiService.query(apiUrl, params)
            .then(({data}) => {
                context.commit("setParasites", data.embedded.parasites)
                context.commit("setPage", data.page)
                context.commit("endLoading")
            })
            .catch((error) => {
                context.commit("setError", true)
                throw error
            })
    },
    createParasite(context, payload) {
        // alert(JSON.stringify(payload))
        return apiService.post(apiUrl, payload)
            .then(({data}) => {
                context.commit("setParasite", data)
            })
            .catch((error) => {
                context.commit("setError", true)
                throw error
            })
    },
    updateParasite(context, [selfLink, payload]) {
        console.log(selfLink)
        console.log(payload)
        let id = apiService.getId(selfLink)
        console.log(id)
        return apiService.update(apiUrl, id, payload)
            .then(({data}) => {
                context.commit("setParasite", data)
            })
            .catch((error) => {
                context.commit("setError", true)
                throw error
            })
    },
    deleteParasite(context, [selfLink, payload]) {
        let id = apiService.getId(selfLink)
        return apiService.delete(apiUrl, id)
            .then(() => {
                alert('deleted successfully')
            })
            .catch((error) => {
                context.commit("setError", true)
                throw error
            })
    },
}

// Mutations
const mutations = {
    setError(state, data){
        state.error = data
    },
    startLoading(state) {
        state.error = false
        state.isLoading = true
    },
    endLoading(state) {
        state.isLoading = false
    },
    setParasites(state, data) {
        state.parasites = data
    },
    setParasite(state, data){
        state.parasite = data
    },
    setPage(state, data){
        state.page.total = data.totalPages -1
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
