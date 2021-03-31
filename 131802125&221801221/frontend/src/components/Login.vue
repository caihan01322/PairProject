<template>
    <div class='login'>
        <a-form
            id="components-form"
            :form="form"
            class="login-form"
            @submit="submitLogin"
        >
            <h1>X J B S</h1>
            <a-alert type="error" message="用户名或密码错误" banner v-if="invalid" />
            <a-form-item>
            <a-input
                v-decorator="[
                'username',
                { rules: [{ required: true, message: '请输入用户名' }] },
                ]"
                placeholder="用户名"
            >
                <a-icon slot="prefix" type="user"/>
            </a-input>
            </a-form-item>
            <a-form-item>
            <a-input
                v-decorator="[
                'password',
                { rules: [{ required: true, message: '请输入密码' }] },
                ]"
                type="password"
                placeholder="密码"
            >
                <a-icon slot="prefix" type="lock" />
            </a-input>
            </a-form-item>
            <a-form-item>
            <a-button type="primary" html-type="submit" class="login-form-button">
                登录
            </a-button>
            </a-form-item>
        </a-form>
    </div>
</template>

<script>

import request from '../requests/user'

export default {
    name: 'Login',
    components: {},
    data () {
        return {
            invalid: false,
        }
    },
    methods: {
        submitLogin(e) {
            e.preventDefault();
            let that = this;
            that.invalid = false;
            this.form.validateFields((err, values) => {
                if (!err) {
                    request.login(values)
                    .then((res)=>{
                        if(res.code != 200) {
                            that.invalid = true;
                        }
                        else {
                            localStorage.setItem("token", res.data.token);
                            localStorage.setItem("username", res.data.username);
                            that.$router.push("/home");
                        }
                    })
                }
            });
        },
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'normal_login' });
    },

}
</script>

<style lang='scss' scoped>
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: #ffffff;
}
.login-form {
    width: 400px;
    padding: 48px;
    box-sizing: border-box;
    background-color: #ffffff;
}
#components-form .login-form-forgot {
  float: right;
}
#components-form .login-form-button {
  width: 100%;
}
</style>