<?php
namespace app\index\controller;
use think\Controller;

class EditDetail extends Controller{
    public function index(){
        $editDetail = [
            "title"=>"abc",
            "abstract"=>"abc",
            "keyword"=>"abc",
            "url"=>"abc"
        ];
        $this->assign("editPaper",json_encode($editDetail));
        return $this->fetch();
    }
}