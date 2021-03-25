<?php
namespace app\index\controller;

use think\Controller;
use think\Db;

class Edit extends Controller
{
    
    public function index()
    {
        $page = 0;
        $limit = 4;
        $data = Db::table('paper')->order('title')->limit($page*$limit,$page*$limit+$limit)->select();
        $this->assign('tableData',json_encode($data));
        return $this->fetch();
    }
    
}
