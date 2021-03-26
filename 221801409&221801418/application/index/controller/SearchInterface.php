<?php

namespace app\index\controller;
use think\Controller;
use think\Db;
use think\db\connector\Mysql;

class SearchInterface extends Controller
{
    public function create(){
        
        $this->assign('name','论文的标题在此！s');
        return view('SearchInterface');
    }

    public function getNoModelData(){
        $Db = new Db;
        $data = Db::table('paper')->select();
        $paper['title']    =    '标题';
        
        return json($data);
    }
}