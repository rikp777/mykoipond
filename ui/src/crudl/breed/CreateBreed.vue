<template>
    <b-form
        @submit="onSubmit"
        @reset="onReset">


        <b-form-group
            id="name"
            label="Name"
            label-for="name">
            <b-form-input
                id="name"
                v-model="form.name"
                type="text"
                required
                placeholder="Enter name"
            ></b-form-input>
        </b-form-group>

        <b-form-group
            id="description"
            label="Description"
            label-for="description">
            <b-form-textarea
                id="description"
                v-model="form.description"
                type="text"
                required
                placeholder="Enter description"
            ></b-form-textarea>
        </b-form-group>
        <div id="error" v-if="error">{{error}}</div>

        <b-button type="submit" variant="primary" id="create">Submit</b-button>
        <b-button type="reset" variant="danger" id="reset">Reset</b-button>
    </b-form>
</template>

<script>
    export default {
        name: "CreateBreed",
        data() {
            return {
                error: '',
                show: true,
                form: {
                    name: '',
                    description: ''
                }
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                let payload = [this.form]
                console.log("Breed Module - updating data", payload)
                this.$store.dispatch("createBreed", payload)
                    .then(() => {
                        this.error = 'success'
                        this.addNotification('success filled', `${this.form.name} successfully created`, 'notification')
                        this.onReset()
                        this.$emit('reloadMode')
                    })
                    .catch(() => {
                        this.error = 'error'
                        this.addNotification('danger filled', `${this.form.name} failed to created`, 'notification')
                    })
            },
            onReset(evt) {
                this.form.name = ''
                this.form.description = ''
                this.addNotification('warning filled', `Reset Form`, 'notification')
            },
            addNotification(type, title, message){
                this.$notify(type, title, message, { duration: 3000, permanent: false })
            }
        }
    }
</script>

<style scoped>

</style>
