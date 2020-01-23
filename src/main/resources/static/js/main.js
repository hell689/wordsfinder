let wordsApi = Vue.resource('/words');
let wordsApiTest = Vue.resource('/words/test');

let app = new Vue({
  el: '#app',
  data: {
    letters: '',
    count: '',
    resMessage: '',
    words: []
  },
  methods: {
    sendButton: function(){
        this.resMessage = '';
        let zapros = {'letters': this.letters, 'count': this.count}
        if(zapros.count <= zapros.letters.length && zapros.count != '' && zapros.letters != '') {
            wordsApi.save(zapros).then(result =>
                result.json().then(data => {
                    this.words = data;
                    if(this.words.length > 0){
                        this.resMessage = "Найдено слов: " + this.words.length;
                    } else {
                       this.resMessage = "К сожалению ничего не нашлось((";
                    }
                })
             )
        } else {
            this.resMessage = "Ошибочный запрос! Возможно количество символов меньше запрошенного.";
        }

     },
     clearButton: function() {
        this.letters = '';
        this.count = '';
        this.words = [];
        this.resMessage = '';
     },
     removeButton: function(index) {
        this.words.splice(index, 1);
     },
     testButton: function(){
         this.resMessage = '';
         let zapros = {'letters': this.letters, 'count': this.count}
         if(zapros.count <= zapros.letters.length && zapros.count != '' && zapros.letters != '') {
             wordsApiTest.save(zapros).then(result =>
                 result.json().then(data => {
                     this.words = data;
                     if(this.words.length > 0){
                         this.resMessage = "Найдено слов: " + this.words.length;
                     } else {
                        this.resMessage = "К сожалению ничего не нашлось((";
                     }
                 })
              )
         } else {
             this.resMessage = "Ошибочный запрос! Возможно количество символов меньше запрошенного.";
         }

      },
  }
});