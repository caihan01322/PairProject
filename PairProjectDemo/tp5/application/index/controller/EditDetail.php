<?php
namespace app\index\controller;
use think\Controller;
use think\Db;

class EditDetail extends Controller{
    
    public function index(){
        return $this->fetch();
    }
    public function updatePaper(){
        $listString = $_COOKIE["updateList"];
        
        $list = json_decode($listString,true);

        
        $oldTitle =  $list["oldTitle"];
        $title = $list["title"];
        $oldLink = $list["oldLink"];
        $link = $list["link"];
        $oldAbstract = $list["oldAbstract"];
        $abstract = $list["abstract"];
        $oldKeyword = $list["oldKeyword"];
        $keyword = $list["keyword"];
        // $form = json_encode($_POST);
        $result = Db::table('paper')
            ->where("title",$oldTitle)
            ->where("link",$oldLink)
            ->where("abstract",$oldAbstract)
            ->where("keyword",$oldKeyword)
            ->update([
                "title"=>$title,
                "link"=>$link,
                "abstract"=>$abstract,
                "keyword"=>$keyword
        ]);
        if($result!==false){
            return $this->success("编辑成功");
        }
        else {
            return $this->error("编辑失败");
        }
    }
}