<?php
namespace app\index\controller;

use think\Controller;
use think\Db;
use think\Request;

class Edit extends Controller
{
    protected $request;
    public function index()
    {
        $count = Db::table('paper')->count();
        $data = Db::table('paper')->select();
        $this->assign('tableData',json_encode($data));
        $this->assign('count',$count);
        return $this->fetch();
    }
    
}
