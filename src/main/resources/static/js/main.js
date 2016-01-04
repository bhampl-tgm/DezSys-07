function KnowledgeViewModel() {
    var self = this;
    self.knowledgeURI = 'http://localhost:8080/knowledge';
    self.knowledge = ko.observableArray();

    self.ajax = function (uri, method, data) {
        var request = {
            url: uri,
            type: method,
            contentType: "application/json",
            //accepts: "application/json",
            cache: false,
            dataType: 'json',
            data: JSON.stringify(data),
            error: function (jqXHR) {
                console.log("ajax error " + jqXHR.status);
            }
        };
        return $.ajax(request);
    };
    self.updateKnowledge = function (knowledge, newKnowledge) {
        var i = self.knowledge.indexOf(knowledge);
        self.knowledge()[i].id(newKnowledge.id);
        self.knowledge()[i].title(newKnowledge.title);
        self.knowledge()[i].content(newKnowledge.content);
        self.knowledge()[i].language(newKnowledge.language);
    };

    self.beginAdd = function () {
        $('#add').modal('show');
    };
    self.add = function (knowledge) {
        self.ajax(self.knowledgeURI, 'POST', knowledge).done(function (data) {
            self.knowledge.push({
                id: ko.observable(data.id),
                title: ko.observable(data.title),
                content: ko.observable(data.content),
                language: ko.observable(data.language)
            });
        });
    };
    self.beginEdit = function (knowledge) {
        editKnowledgeViewModel.setKnowledge(knowledge);
        $('#edit').modal('show');
    };
    self.edit = function (knowledge, data) {
        self.ajax(self.knowledgeURI + '/' + knowledge.id(), 'PUT', data).done(function (res) {
            self.updateKnowledge(knowledge, res);
        });
    };
    self.remove = function (knowledge) {
        self.ajax(self.knowledgeURI + '/' + knowledge.id(), 'DELETE').done(function () {
            self.knowledge.remove(knowledge);
        });
    };
    self.ajax(self.knowledgeURI, 'GET').done(function (data) {
        for (var i = 0; i < data.length; i++) {
            self.knowledge.push({
                id: ko.observable(data[i].id),
                title: ko.observable(data[i].title),
                content: ko.observable(data[i].content),
                language: ko.observable(data[i].language)
            });
        }
    });
}
function AddKnowledgeViewModel() {
    var self = this;
    self.title = ko.observable();
    self.content = ko.observable();
    self.language = ko.observable();

    self.addKnowledge = function () {
        $('#add').modal('hide');
        knowledgeViewModel.add({
            title: self.title(),
            content: self.content(),
            language: self.language()
        });
        self.title("");
        self.content("");
        self.language("");
    }
}
function EditKnowledgeViewModel() {
    var self = this;
    self.title = ko.observable();
    self.content = ko.observable();
    self.language = ko.observable();

    self.setKnowledge = function (knowledge) {
        self.knowledge = knowledge;
        self.title(knowledge.title());
        self.content(knowledge.content());
        self.language(knowledge.language());
        $('edit').modal('show');
    };

    self.editKnowledge = function () {
        $('#edit').modal('hide');
        knowledgeViewModel.edit(self.knowledge, {
            title: self.title(),
            content: self.content(),
            language: self.language()
        });
    }
}
var knowledgeViewModel = new KnowledgeViewModel();
var addKnowledgeViewModel = new AddKnowledgeViewModel();
var editKnowledgeViewModel = new EditKnowledgeViewModel();
ko.applyBindings(knowledgeViewModel, $('#main')[0]);
ko.applyBindings(addKnowledgeViewModel, $('#add')[0]);
ko.applyBindings(editKnowledgeViewModel, $('#edit')[0]);
