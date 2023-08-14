   实体：

```
  @ContentStyle(
            horizontalAlignment = HorizontalAlignmentEnum.LEFT, //水平居左
            verticalAlignment = VerticalAlignmentEnum.CENTER, //垂直居中
            wrapped = BooleanEnum.TRUE)//主要是为了导出时直接换行
    @ExcelProperty("条形码")
    @ColumnWidth(60)
    private WriteCellData<Void> barcode;
```







             public void export(HttpServletResponse response, ExportDTO dto) throws IOException {
            //2023/03/31  导出文件名称 ：  合同名称 +  项目名称 +  批次  +    物资类型    （询价单）
            //合同名称
            //辅材第二个空填写重量，其他第一单位重量
            String contractName = contractListMapper.selectById(dto.getContractListId()).getContractName();
            //项目名称
            String projectName = projectService.getById(dto.getProjectId()).getProjectName();
    
                List<ContractProductDetailsExportVO> list = null;
                //产品，使用清单模板
                //根据合同id，项目id 查询所有的产品采购详情
                if (dto.getMaterialType().equals(MaterialType.WELDING_MATERIALS.getCode())) {
                    List<PurchasePlanDetailsWeldingMaterials> weld = purchasePlanService.getWeld(dto.getContractListId(), dto.getProjectId(), dto.getBatch());
                    list = BeanUtil.copyList(weld, ContractProductDetailsExportVO.class);
                } else {
                    List<PurchasePlanDetailsProductTotal> productTotals = purchasePlanService.getProductTotal(dto.getContractListId(), dto.getProjectId(), dto.getBatch());
                    list = BeanUtil.copyList(productTotals, ContractProductDetailsExportVO.class);
                }
                AtomicInteger num = new AtomicInteger();
    
    
                //计算数量总和
                Double sum = list.stream().filter(item -> !CommonUtil.isNull(item.getPurchaseAmountExport())).mapToDouble(ContractProductDetailsExportVO::getPurchaseAmountExport).sum();
                //计算重量总和
                Double sumWeight = list.stream().filter(item -> !CommonUtil.isNull(item.getPurchaseAmount())).mapToDouble(ContractProductDetailsExportVO::getPurchaseAmount).sum();
                HashMap<String, Object> data = new HashMap<>();
                data.put("sum", sum);
                data.put("sumWeight", sumWeight);
                InputStream resourceAsStream = this.getClass().getResourceAsStream("/static/template/detailedList.xlsx");
                ExcelExportService exportService = new ExcelExportService(contractName + "-" + projectName + "-" + dto.getBatch() + EnumUtil.getValue(MaterialType.class, dto.getMaterialType()) + "询价单", response, resourceAsStream);
                exportService.fill(data, "清单");
    
                list.stream().peek(item -> {
                    try {
                        BufferedImage image = BarCodeUtils.getBarCodeImage(item.getWarehousingNo());
                        item.setBarcode(WriteCellUtil.fillImage(image, "", 5, 5, 5, 5));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
                exportService.fill(new FillWrapper("list", list), "清单");
                exportService.export();
        }