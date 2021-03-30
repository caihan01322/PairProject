import Vue from 'vue'
import VeeValidate, { Validator } from 'vee-validate'
import zh from 'vee-validate/dist/locale/zh_CN' // 引入中文文件

Validator.addLocale(zh)

const config = {
  locale: 'zh_CN',
  fieldsBagName: 'fieldBags',
  errorBagName: 'errorBags' // change if property conflicts.
}

Vue.use(VeeValidate, config)

// 自定义validate
const dictionary = {
  zh_CN: {
    messages: {
      required: (field) => '请输入' + field,
      number: (field) => field + '必须为数值',
      url: () => '请输入正确格式的服务器地址',
      ip: () => '请输入正确的IP地址'
    },
    attributes: {
      ip: 'IP地址',
      httpPort: 'HTTP端口'
    }
  }
}
Validator.updateDictionary(dictionary)

// 1.IPV4地址
Validator.extend('ipAddr', {
  messages: {
    zh_CN: field => '请输入正确的IPV4地址'
  },
  validate: value => {
    return /^((25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))$/.test(value)
  }
})

// 2.端口【0~65535】
Validator.extend('port', {
  messages: {
    zh_CN: field => '请输入正确格式的端口号'
  },
  validate: value => {
    return /^([0-9]|[1-9]\d{1,3}|[1-5]\d{4}|6[0-4]\d{4}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/.test(value)
  }
})
